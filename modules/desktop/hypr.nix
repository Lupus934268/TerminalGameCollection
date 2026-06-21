{ pkgs, inputs, ... }:
{
  wayland.windowManager.hyprland = {
    enable = true;
    package = inputs.hyprland.packages.${pkgs.system}.hyprland;
    xwayland.enable = true;
    plugins = [
      inputs.split-monitor-workspaces.packages.${pkgs.system}.split-monitor-workspaces
    ];
    extraConfig = ''
      dofile("/etc/nixos/modules/desktop/hypr/hyprland.lua")
    '';
  };
}
