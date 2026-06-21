args:
{
  config = {
    imports = [ ./desktop.nix ./fonts.nix ];
  };
  home = {
    imports = [ ./hypr.nix ./portal.nix ];
  };
}
