package cz.zcu.AntPlus2NIXConverter.Interface;

import java.util.stream.Stream;

import org.g_node.nix.Block;
import org.g_node.nix.File;

public interface INixFile {

	void fillNixFile(File nixFile);
}
