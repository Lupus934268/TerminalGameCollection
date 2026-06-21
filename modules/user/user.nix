{ user, pkgs, ... }:
{
  users.users.${user} = {
    isNormalUser = true;
    extraGroups = [ 
      "networkmanager" 
      "wheel" 
      "samba" 
      "video" 
      "render" 
      "input" 
      "uinput" 
    ];
    packages = with pkgs; [ ];
  };
}
