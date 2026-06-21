{ ... }:
{
  security.rtkit.enable = true;
  
  #==== Display Manager ====

  services.displayManager.sddm.enable = true;


  #==== Windows Manager ====

  programs.hyprland.enable = true;
  services.desktopManager.plasma6.enable = true;
  programs.sway = {
    enable = true;
    wrapperFeatures.gtk = true;
  };


  #==== Desktop Services ====

  services = {
    printing.enable = true;

    pipewire = {
      enable = true;
      pulse.enable = true;
      alsa = {
        enable = true;
        support32Bit = true;
      };
    };

    avahi = {
      enable = true;
      nssmdns4 = true;
      openFirewall = true;
    };
  };

}
