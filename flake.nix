{
  description = "A flake for TGC, including a devShell";

  inputs.nixpkgs.url = "github:nixos/nixpkgs?ref=nixos-25.11";

  outputs = { self, nixpkgs }:
    let
      system = "x86_64-linux";
      pkgs = import nixpkgs { inherit system; };
    in {
      devShells.${system}.default = pkgs.mkShell {
        buildInputs = with pkgs; [
          
          # packages installed in the devShell
          neovim
          jdk21
          maven
          jetbrains.idea-oss
          git

        ];
        shellHook = ''

          clear
          echo "Welcome to the devShell!!!"
          echo
          tree
          PS1='\n\[\033[1;35m\][devShell]\[\033[0m\]:\w\n>> '

          '';
      };
    };
}
