{ pkgs, ... }:
{
  boot = {
    kernelParams = [ "video=HDMI-A-2:2560x1440@60" ];
    kernelPackages = pkgs.linuxPackages_latest;
    loader = {
      grub = {
        enable = true;
        device = "nodev";
        efiSupport = true;
      };
      efi.canTouchEfiVariables = true;
    };
  };

  swapDevices = [{
    device = "/var/lib/swapfile";
    size = 16*1024;
  }];
}
