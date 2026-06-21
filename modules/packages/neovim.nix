{ pkgs, ... }:
{
  programs.neovim = {
    enable = true;
    defaultEditor = true;
    plugins = with pkgs.vimPlugins; [ indent-blankline-nvim ];
    extraConfig = ''
      set number
      autocmd FileType nix setlocal shiftwidth=2 tabstop=2 expandtab
      autocmd FileType lua setlocal shiftwidth=4 tabstop=4 expandtab
      lua require("ibl").setup()
    '';
  };
}
