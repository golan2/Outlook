import com.pff.PSTException;
import com.pff.PSTFile;
import com.pff.PSTFolder;
import com.pff.PSTMessage;

import java.util.Vector;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    14/08/14 09:22
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 *
 * https://code.google.com/p/java-libpst/
 * </pre>
 */
public class OpenPST {


  private static final int ROOT_PATH = 0;

  public static void main(String[] args) {
    showFolders("C:\\Users\\golaniz\\Desktop\\TEST.ost");
  }


  public static void showFolders(String filename) {
    try {
      PSTFile pstFile = new PSTFile(filename);
      System.out.println(pstFile.getMessageStore().getDisplayName());
      processFolder(pstFile.getRootFolder(), ROOT_PATH);
    }
    catch (Exception e) {
      e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
  }

  public static void processFolder(PSTFolder folder, int depth) throws PSTException, java.io.IOException
  {
    // the root folder doesn't have a display name
    if (depth > 0) {
      printDepth(depth);
      System.out.println(folder.getDisplayName());
    }
    depth++;

    // go through the folders...
    if (folder.hasSubfolders()) {
      Vector<PSTFolder> childFolders = folder.getSubFolders();
      for (PSTFolder childFolder : childFolders) {
        processFolder(childFolder, depth);
      }
    }

    // and now the emails for this folder
    if (folder.getContentCount() > 0) {
      depth++;
      PSTMessage email = (PSTMessage)folder.getNextChild();
      while (email != null) {
        printDepth(depth);
        System.out.println("Email: "+email.getSubject());
        email = (PSTMessage)folder.getNextChild();
      }
      depth--;
    }
    depth--;
  }

  public static void printDepth(int depth) {
    for (int x = 0; x < depth-1; x++) {
      System.out.print(" | ");
    }
    System.out.print(" |- ");
  }


}
