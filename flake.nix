{
  description = "A flake for TGC, including a devShell";

  inputs.nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-25.11";

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = import nixpkgs { inherit system; config.allowUnfree = true; };
    in {
      
      # devShell for looking cool
      devShells.${system}.default = pkgs.mkShell {
        buildInputs = with pkgs; [
          
          # packages installed in the devShell
          (neovim.override {
            configure = {
              packages.myPlugins = with pkgs.vimPlugins; {
                start = [
                  (nvim-treesitter.withPlugins (p: [ p.rust p.java p.xml p.toml ]))
                  nvim-cmp
                  cmp-nvim-lsp
                ];
              };
            };
          })
          rust-analyzer
          jdt-language-server
          lemminx

          git

          jdk21
          maven
          jetbrains.idea-oss
          
          rustc
          cargo

        ];
        shellHook = ''
          sudo ln -sf $(which stty) /bin/stty 2>/dev/null || true
          clear
          echo "Welcome to the devShell!!!"
          echo
          tree  -a -I '.git/*' -I 'TerminalGameCollection-tui/target/*' --filesfirst
          alias tree="tree  -a -I '.git/*' -I 'TerminalGameCollection-tui/target/*' --filesfirst"
          alias nvim="nvim -u $PWD/tgc-nvim.lua"
          PS1='\n\[\033[1;35m\][devShell]\[\033[0m\]:\w\n>> '
        '';
      };

      packages.${system} = rec {
        
        java-backend = pkgs.maven.buildMavenPackage {
          pname = "TGC-Backend";
          version = "0.0";
          src = ./TerminalGameCollection;
          mvnHash = pkgs.lib.fakeHash;
          installPhase = ''
            mkdir -p $out/share/TGC
            cp target/TerminalGameCollection-*.jar $out/share/TGC/TGC-Backend.jar
          '';
        };

        rust-frontend = pkgs.rustPlatform.buildRustPackage {
          pname = "TGC-Frontend";
          version = "0.0";
          src = ./TerminalGameCollection-tui;
          cargoHash = pkgs.lib.fakeHash;
        };

        default = pkgs.symlinkJoin {
          name = "TGC-App";
          paths = [ rust-frontend ];
          buildInputs = [ pkgs.makeWrapper pkgs.jdk21 ];
          postBuild = ''
            wrapProgram $out/bin/TerminalGameCollection-tui --set TGC_JAVA_BACKEND "${java-backend}/share/TGC/TGC-Backend.jar" --prefix PATH : ${pkgs.jdk21}/bin
          '';
        };

      };

      apps.${system}.default = {
        type= "app";
        program = "${self.packages.${system}.default}/bin/TerminalGameCollection-tui";
      };
    
    };
}
