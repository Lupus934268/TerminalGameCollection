{ pkgs, ... }:
{
  programs = {
    #==== Standard Linux environment ====
    nix-ld.enable = true;
    #==== CLI ====
    neovim = {
      enable = true;
      defaultEditor = true;
    };
    yazi.enable = true;
    git.enable = true;
    bat.enable = true;
    #==== GUI ====
    firefox.enable = true;
    thunderbird.enable = true;
    /* Web-Apps */ chromium = {
      enable = true;
      extraOpts = {
        "WebAppInstallForceList" = [
          {
            "custom_name" = "Discord";
            "create_desktop_shortcut" = true;
            "default_launch_container" = "window";
            "url" = "https://discord.com/app";
          }
          {
            "custom_name" = "Lemokey Launcher";
            "create_desktop_shortcut" = true;
            "default_launch_container" = "window";
            "url" = "https://launcher.lemokey.com";
          }
        ];
      };
    };
    #==== Gaming ====
    gamemode.enable = true;
    steam = {
      enable = true;
      extraCompatPackages = [
        pkgs.proton-ge-bin
        pkgs.dwproton-bin
      ];
    };
  };

  virtualisation.docker.enable = true;

  xdg.portal = {
    enable = true;
    extraPortals = [ pkgs.xdg-desktop-portal-gtk ];
    config = {
      common.default = [ "gtk" ];
    };
  };

  environment = {
    systemPackages = with pkgs; [
      #==== package list ====
      bat
      blockbench
      btop
      chromium
      docker
      fastfetch
      firefox
      gamemode
      gamescope
      ghostty
      git
      hyprlauncher
      hyprpolkitagent
      kdePackages.dolphin
      kdePackages.kio
      kdePackages.kio-extras
      kdePackages.kio-fuse
      kdePackages.qtsvg
      krita
      libreoffice-qt
      modrinth-app
      moonlight-qt
      neovim
      obsidian
      openssh
      oterm
      quickshell
      samba
      scrcpy
      syncthing
      thunderbird
      tree
      upower
      vlc
      wget
      /* wine */ wineWow64Packages.waylandFull
      yazi
    ];
    pathsToLink = [ "/share/applications" "/share/xdg-desktop-portal" ];
  };

  system.activationScripts.packageList = {
    text = ''
      ${pkgs.nix}/bin/nix-store -q --references /run/current-system/sw \
      | ${pkgs.gnused}/bin/sed 's|/nix/store/[^-]*-||' \
      | sort -u > /etc/nixos/modules/packages/pkglist.txt
    '';
  };
}
