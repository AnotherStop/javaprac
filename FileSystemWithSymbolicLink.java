/*
    design a file system
*/
//package com.amazon
import java.util.*;
abstract class FileSystemElement{
    //common attribute
    protected String name;

    //common method
    public abstract void ls();
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }

}

class File extends FileSystemElement{
    public File(String name){
        super();
        this.name = name;
    }

    public void ls(){
        System.out.println(FileSystem.currentIndent + name);
    }
}

class Directory extends FileSystemElement{
    public Directory(String name){
        super();
        this.name = name;
    }

    private Set<Object> elements = new TreeSet<Object>(
        new Comparator<Object>(){
            public int compare(Object a, Object b){
                return ((FileSystemElement)a).getName().compareTo(((FileSystemElement)b).getName());
            }
        }
    );

    public boolean add(Object obj){
        if(elements.contains(obj) == true)
            return false;
        else
            elements.add(obj);
        return true;
    }

    public void ls(){
        System.out.println(FileSystem.currentIndent + name);
        FileSystem.currentIndent += "    "; //four spaces
        Iterator it = elements.iterator();
        while(it.hasNext() == true){
            ((FileSystemElement)it.next()).ls();
        }
        FileSystem.currentIndent = FileSystem.currentIndent.substring(0,FileSystem.currentIndent.length()-4);
    }

}

class SymbolicLink extends FileSystemElement{
    FileSystemElement target;
    public SymbolicLink(String name, FileSystemElement target){
        super();
        this.name = name;
        this.target = target;
    }

    public void ls(){
        System.out.println(FileSystem.currentIndent + name);
        //target.ls()  //comment to avoid infinite ls
    }

    //can add other method to call target's corresponding one
}

public class FileSystemWithSymbolicLink{
    public static String currentIndent = "";
    public static void main(String[] args){
        Directory one = new Directory("dir111/");
        Directory two = new Directory("dir222/");
        Directory three = new Directory("dir333/");
        File a = new File("a");
        File b = new File("b");
        File c = new File("c");
        File d = new File("d");
        File e = new File("e");
        one.add(a);
        one.add(two);
        one.add(b);
        two.add(c);
        two.add(d);
        two.add(three);
        three.add(e);
        SymbolicLink sl = new SymbolicLink("link for dir222", two);
        one.add(sl);
        //traverse file system
        one.ls();
    }
}