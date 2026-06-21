{ inputs, ... }@args:
{
  imports = [

    "${inputs.nixosdir}/hardware-configuration.nix"

    ./modules/core
    (import ./modules/packages args).config
    ./modules/services
    (import ./modules/desktop args).config
    ./modules/user

  ];

  system.stateVersion = "26.05";
}
