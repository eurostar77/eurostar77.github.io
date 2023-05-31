/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bueffeltier.crosscutting.logging.logframe;

import java.util.ArrayList;

/**
 * Eine LogNote kann an einem beliebigen Punkt in dem zu dokumentierenden Code
 * eingesetzt werden. Eine LogNote wird einer Klasse in dem zu loggenden Code
 * zugeordnet.
 * Eine LogNote kann verschiedenen Trackingpfaden zugeordnet werden.
 * todo:
 * @author sveng
 */
public class LogNote
{

    private String className;
    private String methodName;
    private int shift;
    private final String logText;
    private int id;
    private ArrayList<Integer> trackingPaths;
    private boolean start;

    /**
     *
     * @param className
     * @param methodName
     * @param logText
     * @param id
     */
    public LogNote(String className, String methodName, String logText, int id)
    {
        this.start = false;
        this.className = className;
        this.logText = logText;
        this.id = id;
        if (methodName != null)
        {
            this.methodName = methodName;
        } else
        {
            this.methodName = "";
        }

    }

    /**
     *
     */
    public void setStart()
    {
        this.start = true;
    }

    /**
     *
     * @return
     */
    public boolean isStart()
    {
        return this.start;
    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getTrackingPaths()
    {
        return trackingPaths;
    }

    /**
     *
     * @param trackingPath
     */
    public void setTrackingPath(int trackingPath)
    {
        if (!this.trackingPaths.contains(trackingPath))
        {
            this.trackingPaths.add(id, shift);
        }
    }

    /**
     *
     * @return
     */
    public String getClassName()
    {
        return className;
    }

    /**
     *
     * @param className
     */
    public void setClassName(String className)
    {
        this.className = className;
    }

    /**
     *
     * @return
     */
    public String getMethodName()
    {
        return methodName;
    }

    /**
     *
     * @param methodName
     */
    public void setMethodName(String methodName)
    {
        this.methodName = className;
    }

    /**
     *
     * @return
     */
    public int getShift()
    {
        return shift;
    }

    /**
     *
     * @param shift
     */
    public void setShift(int shift)
    {
        this.shift = shift;

    }

    /**
     *
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Schreibt die LogNote. Wenn die selbe Klasse aufeinander folgt, so wird
     * der Klassenname nur in der ersten LogNote angegeben.
     * @param lastClassName
     * @return
     */
    public String getLoggingText()
    {
        return this.logText;
    }

    /**
     * Bedient nur noch die alte LogFrame(Alt) - Klasse
     * @param lastClassName
     * @return
     */
    public String writeAlt(String lastClassName)
    {
        StringBuilder stringBuilder = new StringBuilder();
//            if(!logItems.get(this.getId()-1).getClassType().equals(this.className)){    ...und wenn logItems.size() > 0
        if (lastClassName.equals(this.getClassName()))
        {
            // Keine Klasse schreiben.
        } else
        {
            stringBuilder.append("\n");
            stringBuilder.append(this.writeShiftString(this.shift));
            stringBuilder.append(this.getClassName());
            stringBuilder.append("\n");
        }
        stringBuilder.append(this.writeShiftString(this.shift));
        stringBuilder.append(this.logText);
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }

    /**
     * Ermittelt die Text-Einrückung für die LogNote und gibt sie als String
     * zurück.
     * @param shift
     * @return
     */
    private String writeShiftString(int shift)
    {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < shift; i++)
        {
            string.append("   ");
        }
        return string.toString();
    }
}
