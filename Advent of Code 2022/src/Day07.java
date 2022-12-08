import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day07 implements AoCTask {

	int n = 1105; // input size

	String[] input = new String[n];

	@Override
	public void readInput(Scanner scan) {
		for (int i = 0; i < n; i++) {
			input[i] = scan.nextLine();
		}
	}

	public String[] splitInput(String str, String symbols) {
		ArrayList<String> out = new ArrayList<String>();
		String[] sym = symbols.split("|");
		for (String s : sym) {
			String[] workarr = str.split(s);
			out.add(workarr[0]);
		}
		;
		return (String[]) out.toArray();
	}

	@Override
	public String task1() {
		File root = ordertoStructure(input);
		int out = atMost(100000, root);
		return "" + out;
	}

	@Override
	public String task2() {
		File root = ordertoStructure(input);

		int space_used = root.getSize();
		int min = 30000000;
		int space = 70000000;
		int space_free = space - space_used;
		int space_needed = min - space_free;
		int result = space_used;
		
		if (space_needed < 0) return "" + 0;

		ArrayList<File> queue = new ArrayList<File>();
		queue.addAll(root.childs);

		while (queue.size() > 0) {
			File el = queue.get(0);
			if (el.type.equals("dir")) {
				queue.addAll(el.childs);
				if (result > el.getSize() &&  el.getSize() > space_needed ) {
					result = el.getSize();
			}}
			queue.remove(0);
		} 
		return "" + result;
	}

	public int atMost(int n, File file) {

		int result = 0;
		ArrayList<File> queue = new ArrayList<File>();
		queue.addAll(file.childs);

		while (queue.size() > 0) {
			File el = queue.get(0);
			if (el.type.equals("dir")) {
				queue.addAll(el.childs);

				if (el.getSize() <= n)
					result = result + el.getSize();
			}
			queue.remove(0);
		}
		return result;
	}

	public File ordertoStructure(String[] arr) {
		File out = new File();
		File current = out;

		for (int i = 0; i < arr.length; i++) {

			// order
			if (arr[i].charAt(0) == '$') {
				String order = arr[i].substring(2, 4);
				// cd
				if (order.equals("cd")) {
					String name = arr[i].substring(5);
					if (name.equals("..")) {
						current = current.getParent();
					} else { // dir
						File newDir = new File(name, "dir", current);
						if (compareFile(newDir, current.getChilds()) == -1)
							current.addChild(newDir);
						current = current.getChilds().get(compareFile(newDir, current.getChilds()));
					}
				}

				// ls
			} else {
				String[] str = arr[i].split(" ");
				if (str[0].equals("dir")) { // dir
					String name = str[1];
					File newFile = new File(name, "dir", current);
					if (compareFile(newFile, current.getChilds()) == -1)
						current.addChild(newFile);

				} else {
					int size = Integer.valueOf(str[0]);
					String name = str[1];
					File newFile = new File(name, "file", current, size);
					if (compareFile(newFile, current.getChilds()) == -1)
						current.addChild(newFile);

				}
				;
			}
		}

		//print(out);

		return out;

	}

	public int compareFile(File file, ArrayList<File> list) {
		for (int i = 0; i < list.size(); i++) {
			if (file.name.equals(list.get(i).name)) {
				return i;
			}
		}
		return -1;
	}

	public class File {
		public String name;

		public String getName() {
			return name;
		}

		public String type = ""; // dir/file

		public String getType() {
			return type;
		}

		public boolean isDir() {
			return type.equals("dir");
		}

		public boolean isFile() {
			return type.equals("file");
		}

		public int size = 0;

		File() {
		};

		File(String name, String type) {
			this.name = name;
			this.type = type;
		};

		File(String name, String type, File parent) {
			this.name = name;
			this.type = type;
			this.parent = parent;
		};

		File(String name, String type, File parent, int size) {
			this.name = name;
			this.type = type;
			this.parent = parent;
			this.size = size;
		};

		public int getSize() {
			if (type.equals("dir"))
				size = 0;
			ArrayList<File> queue = new ArrayList<File>();
			queue.addAll(childs);
			while (queue.size() > 0) {
				File el = queue.get(0);
				if (el.type.equals("dir")) {
					queue.addAll(el.childs);
				}
				if (el.type.equals("file")) {
					size = size + el.getSize();
				}
				queue.remove(0);
			}
			return size;
		}

		public File parent;

		public File getParent() {
			return parent;
		}

		public ArrayList<File> childs = new ArrayList<File>();

		public void addChild(File child) {
			childs.add(child);
		}

		public ArrayList<File> getChilds() {
			return childs;
		}
	}

	public void print(File file) {
		printChilds("", file);
	}

	public void printChilds(String s, File file) {
		s = s + "  ";
		for (File el : file.childs) {
			System.out.println(s + "-" + el.name + " size=" + el.getSize() + " " + el.getChilds().size());
			printChilds(s, el);
		}
	}

	File createDataStructure(String[] arr) {
		String[] first = arr[0].split("- |, | \\(|=|\\)");
		String name_first = first[1];
		String type_first = first[2];
		System.out.println(Arrays.toString(first));

		File out = new File(name_first, type_first);

		File currentParent = out;
		int height = 0; // current height in the file structure

		for (int i = 1; i < arr.length; i++) {
			String[] str = arr[i].split("- |, | \\(|=|\\)");
			int el_height = str[0].length() / 2; // height of the el in the file structure
			String name = str[1];
			String type = str[2];
			File parent = currentParent;

			if (height < el_height - 1) {
				ArrayList<File> childs = parent.getChilds();
				parent = childs.get(childs.size() - 1);
				height = height + 1;

			}
			if (height > el_height - 1) {
				parent = currentParent.getParent();
				height = height - 1;

			}
			currentParent = parent;

			if (type.equals("dir")) {
				parent.addChild(new File(name, type, parent));
			}
			if (type.equals("file")) {
				int size = Integer.valueOf(str[4]);
				parent.addChild(new File(name, type, parent, size));

			}

		}
		return out;
	}
}
