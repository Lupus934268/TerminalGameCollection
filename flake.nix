{
  description = "A flake for TGC, including a devShell";

  inputs.nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-25.11";

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = import nixpkgs { inherit system; config.allowUnfree = true; };
    in {
      devShells.${system}.default = pkgs.mkShell {
        buildInputs = with pkgs; [
          
          # packages installed in the devShell
          neovim
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
          tree -a -I '.git/*' --filesfirst
          alias nvim="nvim -u $PWD/tgc-nvim.lua"
          PS1='\n\[\033[1;35m\][devShell]\[\033[0m\]:\w\n>> '
        '';
      };
    };
}
