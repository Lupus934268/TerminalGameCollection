{ pkgs, ... }:
{
  fonts.packages = with pkgs; [
    
    # chinese fonts
    noto-fonts-cjk-sans
    noto-fonts-cjk-serif

  ];
}
