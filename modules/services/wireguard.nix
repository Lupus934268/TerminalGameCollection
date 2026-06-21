{ secrets, hostname, lib, ... }:
{
  networking.wireguard.interfaces.wg0 = lib.mkIf (hostname == "nixos-FRMW") {
    ips = [ "10.0.0.3/32" ];
    privateKeyFile = "${secrets}/${hostname}/wireguard/private.key";
    peers = [
      {
        publicKey = "Evt/GQ/r/EPm4yeyLn7X6XSL3v/znMO3p6tM/9SPrm0=";
        allowedIPs = [ "10.0.0.0/24" "192.168.178.0/24" ];
        endpoint = "93.197.165.200:51820";
        persistentKeepalive = 25;
      }
    ];
  };
}
