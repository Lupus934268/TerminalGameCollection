{ user, nixosVersion, ... }@args:
{
  imports = [
    (import ./modules/desktop args).home
    (import ./modules/packages args).home
  ];

  home.homeDirectory = "/home/${user}";
  home.stateVersion = "26.05";
}
