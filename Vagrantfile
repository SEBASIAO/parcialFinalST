Vagrant.configure("2") do |config|
config.vm.define :firewall1 do |firewall1|
 firewall1.vm.box = "bento/centos-7.8"
 firewall1.vm.network :private_network, ip: "209.191.50.3"
 firewall1.vm.network :public_network, bridge: "Realtek PCIe GbE Family Controller #2", ip: "192.168.1.50"
 firewall1.vm.provision "shell", path: "firewallscript.sh"
 firewall1.vm.hostname = "firewall"
 end
config.vm.define :servidorpSTRM do |servidorpSTRM|
 servidorpSTRM.vm.box = "bento/centos-7.8"
 servidorpSTRM.vm.network :private_network, ip: "192.168.50.2"
 servidorpSTRM.vm.provision "shell", path: "stream.sh"
 servidorpSTRM.vm.hostname = "servidorpSTRM"
 end
end
