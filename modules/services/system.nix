{ ... }:
{
  services = {
    openssh.enable = true;
    upower.enable = true;
    samba.enable = true;

    udev.extraRules = ''
      # Lemokey / Keychron — keyboard (VID 3434)
      SUBSYSTEM=="hidraw", ATTRS{idVendor}=="3434", MODE="0660", GROUP="input"
      # Lemokey — USB dongle / mouse receiver (VID 362d)
      SUBSYSTEM=="hidraw", ATTRS{idVendor}=="362d", MODE="0660", GROUP="input"
    '';

    flatpak = {
      enable = true;
      packages = [ "com.github.flxzt.rnote" ];
    };
  };
}
