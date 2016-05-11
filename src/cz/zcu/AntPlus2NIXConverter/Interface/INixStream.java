package cz.zcu.AntPlus2NIXConverter.Interface;

import java.util.stream.Stream;

import org.g_node.nix.File;

public interface INixStream {

	Stream<File> createNixFile(String fileName);
}
