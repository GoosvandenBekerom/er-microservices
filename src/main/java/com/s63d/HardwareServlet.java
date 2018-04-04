package com.s63d;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;

public class HardwareServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        Runtime runtime = Runtime.getRuntime();
        NumberFormat number = NumberFormat.getInstance();

        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        out.println("OS:");
        out.println("name: "+System.getProperty("os.name"));
        out.println("version: "+System.getProperty("os.version"));
        out.println("architecture: "+System.getProperty("os.arch"));

        out.println("MEMORY:");
        out.println("free memory: "+number.format(freeMemory / 1024));
        out.println("allocated memory: "+number.format(allocatedMemory / 1024));
        out.println("max memory: "+number.format(maxMemory / 1024));
        out.println("total free memory: "+number.format((freeMemory + (maxMemory - allocatedMemory)) / 1024));

        out.println("DISKS:");
        for (File root : File.listRoots()) {
            out.println("File system root: "+root.getAbsolutePath());
            out.println("Total space (bytes): "+root.getTotalSpace());
            out.println("Free space (bytes): "+root.getFreeSpace());
            out.println("Usable space (bytes): "+root.getUsableSpace());
        }
    }
}
