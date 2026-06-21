{ ... }:
{
  xdg.configFile."xdg-desktop-portal/portals.conf".text = ''
    [preferred]
    default=gtk
  '';
}
