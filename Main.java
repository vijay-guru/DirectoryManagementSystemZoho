import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Directory currentDirectory;
    static Directory root = new Directory("~",null);

    public static void makeDirectory(String newDirectoryName){
        Directory newDirectory = new Directory(newDirectoryName,currentDirectory);
        currentDirectory.subDirectories.add(newDirectory);

        System.out.println("New Directory : "+newDirectoryName +", created successfully");
    }
    public static void changeDirectory(String directoryName){
        boolean flag = false;
        List<Directory> directories = currentDirectory.subDirectories;
        for(int i=0 ; i<directories.size();i++){
            if(directories.get(i).directoryName.equals(directoryName)){
                currentDirectory = directories.get(i);
                flag = true;
                break;
            }
        }
        if(flag){
            System.out.println("Directory changed to "+directoryName);
        }
        else{
            System.out.println("Directory not found ! . Try again with correct directory name.");
        }
    }
    public static void toPreviousDirectory(){
        currentDirectory = currentDirectory.previousDirectory;
        System.out.println("Directory path changed to previous directory");
    }
    public static void changeDirectoryByFullPath(String pathName[]){
        int pathSize = pathName.length;
        boolean subPath = false;
        Directory temp = root;
        int index = 0;
        for(int i=1;i<=pathSize;i++){
            subPath = false;
            for(int j=0;j<temp.subDirectories.size();j++){
                if(temp.subDirectories.get(i).directoryName.equals(pathName[index])){
                    temp = temp.subDirectories.get(i);
                    subPath = true;
                    break;
                }
            }
            if(!subPath){
                System.out.println("Path not found !!!");
                break;
            }
            index++;
        }
        if(subPath){
            currentDirectory = temp;
            System.out.println("Directory successfully changed !!!!");
        }
    }
    public static void changeDirectoryViaCrrentPath(String pathName[]){
        int i = 0;
        String currentDirName = currentDirectory.directoryName;
        boolean flag = false;
        for(i=0;i<pathName.length;i++){
            if(pathName[i].equals(currentDirName)){
                flag = true;
                break;
            }
        }
        if(!flag){
            System.out.println("Given directory path doest not contain current path name !!! . Try Again");
            return ;
        }
        Directory temp = currentDirectory;
        boolean subPath = false;
        for(int j=i;j<pathName.length;j++){
            subPath = false;
            for(i=0;i>temp.subDirectories.size();i++){
                if(temp.subDirectories.get(i).directoryName.equals(pathName[j])){
                    temp = temp.subDirectories.get(i);
                    subPath = true;
                    break;
                }
            }
            if(!subPath){
                System.out.println("Path not found !!!");
                break;
            }

        }
        if(subPath){
            currentDirectory = temp;
            System.out.println("Directory successfully changed !!!!");
        }
    }
    public static void listDirectories(){
        System.out.println("Directories under current directory : ");
        if(currentDirectory.subDirectories.size() == 0){
            System.out.println("Empty !!!");
            return ;
        }
        for(int i=0;i<currentDirectory.subDirectories.size();i++){
            System.out.println(currentDirectory.subDirectories.get(i).directoryName);
        }
    }
    public static void printPresentWorkingDir(){
        ArrayList<String> pathName = new ArrayList<>();
        Directory currDirectory = currentDirectory;
        while(currDirectory!=null){
            pathName.add(currDirectory.directoryName);
            currDirectory = currDirectory.previousDirectory;
        }
        for(int i=pathName.size()-1;i>=0;i--){
            System.out.print(pathName.get(i)+"/");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        currentDirectory = root;
        System.out.println("_______Welcome to Directory Management System_______");
        System.out.println("Enter the command below : (q for exit)");

        while(true){

            String command = new String();
            command = sc.nextLine();
            if(command.equals("q")){
                break;
            }
            if(command.startsWith("md ") && command.length() >3){
                makeDirectory(command.substring(3));
            }
            else if(command.equals("cd ..")){
                toPreviousDirectory();
            }
            else if(command.startsWith("cd ~/")){
                String path = command.substring(5);
                String pathArray[] = path.split("/");
                changeDirectoryByFullPath(pathArray);
            }
            else if(command.startsWith("cd ./")){
                String path = command.substring(5);
                String pathArray[] = path.split("/");
                changeDirectoryViaCrrentPath(pathArray);
            }
            else if(command.startsWith("cd ") && command.length() >3){
                changeDirectory(command.substring(3));
            }
            else if(command.equals("ls")){
                listDirectories();
            }
            else if(command.equals("pwd")){
                printPresentWorkingDir();
            }
            else{
                System.out.println("Invalid command . Try Again !!!");
            }
        }

    }
}
