import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class FindTestFiles
{
	public static void main(String[] args)
	{
		List fileList = new ArrayList();
		fillPath(args[0],"",fileList);

		for(int i = 0;i<fileList.size();i++)
		{
			File f = (File) fileList.get(i);
			String name = f.getName();
			String path = f.getPath();
			System.out.println("    <java-class>" + path.substring(2,path.length()-5).replace('/','.') + "</java-class>");
		} 	
	}

	public static void fillPath(String path, String name, List current)
	{
		File f = new File(path);

		if(f.isFile())
		{
			current.add(f);
		}

		else if (f.isDirectory())
		{
			if(!path.endsWith(File.separator))
			{
				path += File.separator;
			}

			String[] list = f.list();

			for (int i = 0; (list != null) && (i < list.length); i++)
			{
				fillPath(path + list[i], list[i], current);
			}
		}
	}
}
