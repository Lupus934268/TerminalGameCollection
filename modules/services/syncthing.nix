{ secrets, hostname, lib, ... }:
{
  services.syncthing = {
    enable = true;
    user = "lupus";
    dataDir = "/home/lupus";
    configDir = "/home/lupus/.config/syncthing";
    cert = "${secrets}/${hostname}/syncthing/cert.pem";
    key = "${secrets}/${hostname}/syncthing/key.pem";
    openDefaultPorts = true;
    settings = {
      devices = {
        nixos-PC.id = "DDGWKOP-AAGWJ3A-HEVF3D2-I5X7ATJ-LOP7MZW-DTJTQ6Z-RN6BSXV-2V5JHQT";
        nixos-FRMW.id = "PGDDY26-RIMC3X6-XWPC7CY-5MSFQ3B-5QDJOSO-DDPYSDF-RATWBSJ-TG7RIAT";
        phone.id = "LE2XNQO-XUJJJ62-JULPJV5-XHPXEOT-6JUIJ26-ZSJJLWE-6N3AVOZ-6SLUQQZ";
      };
      folders = {
        nixos-flake = {
          label = "NixOS Flake";
          path = "/etc/nixos";
          devices = [ "nixos-PC" "nixos-FRMW" ];
          ignorePatterns = [
            "hardware-configuration.nix"
            "flake.lock"
            ".gitignore"
            ".git"
          ];
        };
      };
    };
  };

  systemd.tmpfiles.rules = [
    "Z /etc/nixos 0755 lupus users - -"
  ];
}
