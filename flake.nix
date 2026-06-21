{
  #============
  #== Inputs ==
  #============

  inputs = {

    #===== NixOS =====

    nixpkgs = {
      url = "github:nixos/nixpkgs?ref=nixos-unstable";
    };

    home-manager = {
      url = "github:nix-community/home-manager/master";
      inputs.nixpkgs.follows = "nixpkgs";
    };

    secrets = {
      url = "git+http://192.168.178.92:3000/lupus/nixFlakes-secrets?ref=default";
      flake = false;
    };

    nixosdir = {
      url = "path:/etc/nixos";
      flake = false;
    };


    #===== Hyprland =====

    hyprland = {
      url = "github:hyprwm/Hyprland";
    };

    split-monitor-workspaces = {
      url = "github:zjeffer/split-monitor-workspaces";
      inputs.hyprland.follows = "hyprland";
    };


    #===== HoyoVerse Games =====

    aagl = {
      url = "github:ezKEa/aagl-gtk-on-nix";
      inputs.nixpkgs.follows = "nixpkgs";
    };


    #===== Flatpak =====

    nix-flatpak = {
      url = "github:gmodena/nix-flatpak";
    };

  };


  #=============
  #== Outputs ==
  #=============

  outputs = { self, ... } @ inputs:

    let

      vars = {
        user = "lupus";
        system = "x86_64-linux";
      };

      mkSystem = hostname: inputs.nixpkgs.lib.nixosSystem {
        system = vars.system;
        specialArgs = vars // { inherit hostname inputs; secrets = inputs.secrets; };
        modules = [

          # nixos config
          ./configuration.nix

          # home manager config
          inputs.home-manager.nixosModules.default {
            home-manager = {
              useGlobalPkgs = true;
              useUserPackages = true;
              backupFileExtension = "backup";
              extraSpecialArgs = vars // { inherit inputs; };
              users.${vars.user} = import ./home.nix;
            };
          }

          { # HoyoVerse Launchers
            imports = [ inputs.aagl.nixosModules.default ];
            nix.settings = inputs.aagl.nixConfig;
            programs = {
              honkers-launcher.enable = false;
              anime-game-launcher.enable = false;
              honkers-railway-launcher.enable = true;
              sleepy-launcher.enable = false;
            };
          }

          # Flatpak for rnote
          inputs.nix-flatpak.nixosModules.nix-flatpak

        ];
      };

    in {
      nixosConfigurations = {
        nixos-PC   = mkSystem "nixos-PC";
        nixos-FRMW = mkSystem "nixos-FRMW";
      };
    };
}
