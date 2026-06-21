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
        nixos-FRMW.id = "4HMZ4QS-7A5H57N-TWNKQJZ-SRPMSHY-HR53Z4G-LC7DGI4-TU4OWOJ-WBKGPQ3";
        Mix-Flip.id = "LE2XNQO-XUJJJ62-JULPJV5-XHPXEOT-6JUIJ26-ZSJJLWE-6N3AVOZ-6SLUQQZ";
      };
      folders = {
        nixos-flake = {
          label = "NixOS Flake";
          path = "/etc/nixos";
          devices = [ "nixos-PC" "nixos-FRMW" ];
          ignorePatterns = [
            "hardware-configuration.nix"
            "flake.lock"
            "modules/packages/pkglist.txt"
          ];
        };
      };
    };
    overrideFolders = false;
    overrideDevices = false;
  };

  systemd.tmpfiles.rules = [
    "Z /etc/nixos 0755 lupus users - -"
  ];
}
