{ pkgs, ... }:
{
  systemd.services.ydotoold = {
    description = "ydotool daemon";
    wantedBy = [ "multi-user.target" ];
    after = [ "multi-user.target" ];
    serviceConfig = {
      Type = "simple";
      ExecStart = "${pkgs.ydotool}/bin/ydotoold";
      Restart = "on-failure";
    };
  };

  systemd.services.wacom-buttons = let
    pythonEnv = pkgs.python3.withPackages (p: [ p.evdev ]);
    script = pkgs.writeText "wacom-buttons.py" ''
      import evdev, subprocess, select, sys

      YDOTOOL = "${pkgs.ydotool}/bin/ydotool"

      devs = {}
      for path in evdev.list_devices():
          try:
              d = evdev.InputDevice(path)
              if d.name in ("Wacom Movink 13 Pad", "Wacom Movink 13 Pen"):
                  devs[d.fd] = d
                  print(f"Watching: {d.name} at {path}", flush=True)
          except Exception:
              pass

      if not devs:
          print("No Wacom devices found", flush=True)
          sys.exit(1)

      while True:
          r, _, _ = select.select(devs, [], [])
          for fd in r:
              try:
                  for event in devs[fd].read():
                      if event.type == evdev.ecodes.EV_KEY and event.value == 1:
                          print(f"Button code: {event.code}", flush=True)
                          if event.code == 257:
                              subprocess.run([YDOTOOL, "key", "29:1", "42:1", "21:1", "21:0", "42:0", "29:0"])
                          elif event.code == 329:
                              subprocess.run([YDOTOOL, "key", "29:1", "21:1", "21:0", "29:0"])
              except BlockingIOError:
                  pass
    '';
  in {
    description = "Wacom tablet button bindings";
    wantedBy = [ "multi-user.target" ];
    after = [ "multi-user.target" "ydotoold.service" ];
    serviceConfig = {
      Type = "simple";
      Restart = "on-failure";
      RestartSec = "5s";
      ExecStart = "${pythonEnv}/bin/python3 ${script}";
    };
  };
}
