args:
{
  config = {
    imports = [ ./programs.nix ];
  };
  home = {
    imports = [ ./git.nix ./neovim.nix ];
  };
}
