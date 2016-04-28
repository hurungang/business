package com.runtech.log;

/*
 * Copyright (C) The Apache Software Foundation. All rights reserved.
 *
 * This software is published under the terms of the Apache Software
 * License version 1.1, a copy of which has been included with this
 * distribution in the LICENSE.APL file.  */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

/**
 * This file is adapted directly from the standard DailyRollingFileAppender
 * except that it archives the rolled file in a zip format after rolling it.<P>
 *
 * I would've used inheritance here and then overriden the rollup() method, but some
 * of the fields which are required in rollup() are not visible to subclasses.
 * Hence the lovely cut-n-paste extension pattern ;)
 *
 */
public class DailyRollingFileAppender extends FileAppender
{ 
   // The code assumes that the following constants are in a increasing
   // sequence.
   static final int TOP_OF_TROUBLE = -1;
   static final int TOP_OF_MINUTE = 0;
   static final int TOP_OF_HOUR = 1;
   static final int HALF_DAY = 2;
   static final int TOP_OF_DAY = 3;
   static final int TOP_OF_WEEK = 4;
   static final int TOP_OF_MONTH = 5;


   /**
    A string constant used in naming the option for setting the
    filename pattern. Current value of this string constant is
    <strong>DatePattern</strong>.

    @deprecated Options are now handled using the JavaBeans paradigm.
    This constant is not longer needed and will be removed in the
    <em>near</em> term.
    */
   static final public String DATE_PATTERN_OPTION = "DatePattern";

   /**
    The date pattern. By default, the pattern is set to
    "'.'yyyy-MM-dd" meaning daily rollover.
    */
   private String datePattern = "'.'yyyy-MM-dd";

   /**
    The actual formatted filename that is currently being written to.
    */
   private String scheduledFilename;

   /**
    The timestamp when we shall next recompute the filename.
    */
   private long nextCheck = System.currentTimeMillis() - 1;

   Date now = new Date();

   SimpleDateFormat sdf;

   RollingCalendar rc = new RollingCalendar();

   int checkPeriod = TOP_OF_TROUBLE;

   /**
    The default constructor does nothing. */
   public DailyRollingFileAppender()
   {
   }

   /**
    Instantiate a <code>DailyRollingFileAppender</code> and open the
    file designated by <code>filename</code>. The opened filename will
    become the ouput destination for this appender.

    */
   public DailyRollingFileAppender(Layout layout,
		                                     String filename,
		                                     String datePattern) throws IOException
   {
		super(layout, filename, true);
		this.datePattern = datePattern;
		activateOptions();
   }

   /**
    The <b>DatePattern</b> takes a string in the same format as
    expected by [EMAIL PROTECTED] SimpleDateFormat}. This options determines the
    rollover schedule.
    */
   public void setDatePattern(String pattern)
   {
		datePattern = pattern;
   }

   /** Returns the value of the <b>DatePattern</b> option. */
   public String getDatePattern()
   {
		return datePattern;
   }

   /**
    @deprecated We now use JavaBeans introspection to configure
    components. Options strings are no longer needed.

    */
   public String[] getOptionStrings()
   {
		return new String[0];  // this is deprecated...
   }

   /**
    Set the options for the [EMAIL PROTECTED] 
org.apache.log4j.DailyRollingFileAppender}
    instance.

    <p>The <b>DatePattern</b> takes a string in the same format as
    expected by [EMAIL PROTECTED] SimpleDateFormat}. This options determines the
    rollover schedule.

    <p>Be sure to refer to the options in the super classes [EMAIL PROTECTED]
    FileAppender}, [EMAIL PROTECTED] org.apache.log4j.WriterAppender} and in 
particular the
    <b>Threshold</b> option in [EMAIL PROTECTED] org.apache.log4j.AppenderSkeleton}.

    </ul>

    @deprecated Use the setter method for the option directly instead
    of the generic <code>setOption</code> method.
    */
   public void setOption(String key, String value)
   {
		throw new RuntimeException(getClass().getName() + ".setOption is NOT implemented");
   }

   public void activateOptions()
   {
		super.activateOptions();
		if(datePattern != null && fileName != null)
		{
		   now.setTime(System.currentTimeMillis());
		   sdf = new SimpleDateFormat(datePattern);
		   int type = computeCheckPeriod();
		   printPeriodicity(type);
		   rc.setType(type);
		   scheduledFilename = fileName + sdf.format(now);
		}
		else
		{
		   LogLog.error("Either Filename or DatePattern options are not set for [" +
		           name + "].");
		}
   }

   void printPeriodicity(int type)
   {
		switch(type)
		{
		   case TOP_OF_MINUTE:
		      LogLog.debug("Appender [" + name + "] to be rolled every minute.");
		      break;
		   case TOP_OF_HOUR:
		      LogLog.debug("Appender [" + name
		              + "] to be rolled on top of every hour.");
		      break;
		   case HALF_DAY:
		      LogLog.debug("Appender [" + name
		              + "] to be rolled at midday and midnight.");
		      break;
		   case TOP_OF_DAY:
		      LogLog.debug("Appender [" + name
		              + "] to be rolled at midnight.");
		      break;
		   case TOP_OF_WEEK:
		      LogLog.debug("Appender [" + name
		              + "] to be rolled at start of week.");
		      break;
		   case TOP_OF_MONTH:
		      LogLog.debug("Appender [" + name
		              + "] to be rolled at start of every month.");
		      break;
		   default:
		      LogLog.warn("Unknown periodicity for appender [" + name + "].");
		}
   }


   int computeCheckPeriod()
   {
		RollingCalendar c = new RollingCalendar();
		// set sate to 1970-01-01 00:00:00 GMT
		Date epoch = new Date(0);
		if(datePattern != null)
		{
		   for (int i = TOP_OF_MINUTE; i <= TOP_OF_MONTH; i++)
		   {
		      String r0 = sdf.format(epoch);
		      c.setType(i);
		      Date next = new Date(c.getNextCheckMillis(epoch));
		      String r1 = sdf.format(next);
		      //LogLog.debug("Type = "+i+", r0 = "+r0+", r1 = "+r1);
		      if(r0 != null && r1 != null && !r0.equals(r1))
		      {
		         return i;
		      }
		   }
		}
		return TOP_OF_TROUBLE; // Deliberately head for trouble...
   }

   /**
    Rollover the current file to a new file.
    */
   void rollOver() throws IOException
   {

		/* Compute filename, but only if datePattern is specified */
		if(datePattern == null)
		{
		   errorHandler.error("Missing DatePattern option in rollOver().");
		   return;
		}

		String datedFilename = fileName + sdf.format(now);
		if(scheduledFilename.equals(datedFilename))
		   return;

		// close current file, and rename it to datedFilename
		this.closeFile();

		File target = new File(scheduledFilename);
		if(target.exists())
		{
		   target.delete();
		}

		File file = new File(fileName);
		
		//�����ļ�
		try
		{
			FileInputStream fis=new FileInputStream(file);
			FileOutputStream fos=new FileOutputStream(target);
			//��ʼ����������
			int stmp;
			while ((stmp=fis.read())!=-1)
			{
				fos.write((char)stmp);
			}
			//�ͷ���Դ����ֹ�������ļ�
			fos.close();
			fis.close();
			fos=null;
			fis=null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		FileOutputStream fos=new FileOutputStream(file);
		fos.close();
		//���ԭ�ļ�
	  /*
		file.renameTo(target);
	  */
		LogLog.debug(fileName + " -> " + scheduledFilename);

		// This will also close the file. This is OK since multiple
		// close operations are safe.

		// LWH: Recent change...
		this.setFile(fileName);
		this.activateOptions();


		//--- archive the file...
		this.archiveFile(target);

		//--- HACK: we can't delete the file immediately, so we're spawning a thread to
		//    delete it five minutes later...
		String targetPath = target.getAbsolutePath();

		target = null;
		file = null;

		Thread deleteThread = new Thread(new DeleteFileLater(targetPath, 5 * 60 * 1000),"DeleteOldLogFileThread");
		deleteThread.start();


		scheduledFilename = datedFilename;
   }

   /**
    * for some reason we can't delete the file immediately...
    */
   private static class DeleteFileLater implements Runnable
   {
		private String targetFilePath;
		private long timeToSleepInMillis;

		public DeleteFileLater(String aTargetFilePath, long aTimeToSleepInMillis)
		{
		   targetFilePath = aTargetFilePath;
		   timeToSleepInMillis = aTimeToSleepInMillis;
		}

		public void run()
		{
		   try
		   {
		      Thread.sleep(timeToSleepInMillis);
		      File target = new File(targetFilePath);

		      System.out.println("\n\n*** deleting [" + targetFilePath + "]\n\n");
		      if(target.delete())
		      {
		         System.out.println("*** Successfully deleted [" + targetFilePath + "]");
		      }
		      else
		      {
		         System.out.println("*** Failed to delete [" + targetFilePath + "]");
		      }
		   }
		   catch(InterruptedException e)
		   {
		      LogLog.debug("Interrupted", e);
		   }
		}
   }


   /**
    * create a zip file which contains the target file, then delete the target...
    * @param target
    */
   void archiveFile(File target)
   {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		try
		{
		   // Init Zip file stream.
		   fos = new FileOutputStream(target.getAbsolutePath() + ".zip");
		   zos = new ZipOutputStream(fos);

		   // Create a file input stream and a buffered input stream.
		   FileInputStream fis = new FileInputStream(target.getAbsolutePath());
		   BufferedInputStream bis = new BufferedInputStream(fis);

		   // Create a Zip Entry and put it into the archive (no data yet).
		   ZipEntry fileEntry = new ZipEntry(target.getName());
		   zos.putNextEntry(fileEntry);

		   // Create a byte array object named data and declare byte count variable.
		   byte[] data = new byte[4096];
		   int byteCount;
		   // Create a loop that reads from the buffered input stream and writes
		   // to the zip output stream until the bis has been entirely read.
		   while ((byteCount = bis.read(data, 0, 4096)) > -1)
		   {
		      zos.write(data, 0, byteCount);
		   }

		   // Close Zip output Stream
		   zos.flush();
		   zos.close();
		   fos.close();
		}
		catch(IOException e)
		{
		   LogLog.error(e.getMessage(), e);
		}
   }

   /**
    This method differentiates DailyRollingFileAppender from its
    super class.
    */
   protected void subAppend(LoggingEvent event)
   {
		long n = System.currentTimeMillis();
		if(n >= nextCheck)
		{
		   now.setTime(n);
		   nextCheck = rc.getNextCheckMillis(now);
		   try
		   {
		      rollOver();
		   }
		   catch(IOException ioe)
		   {
		      LogLog.error("rollOver() failed.", ioe);
		   }
		}
		super.subAppend(event);
   }
}

/**
 RollingCalendar is a helper class to
 DailyRollingFileAppender. Using this class, it is easy to compute
 and access the next Millis().

 It subclasses the standard [EMAIL PROTECTED] GregorianCalendar}-object, to
 allow access to the protected function getTimeInMillis(), which it
 then exports.

 @author <a HREF="mailto:[EMAIL PROTECTED]">Eirik Lygre</a> */

class RollingCalendar extends GregorianCalendar
{
	private static final long serialVersionUID = -5907684418844204418L;
	int type = DailyRollingFileAppender.TOP_OF_TROUBLE;

   void setType(int type)
   {
		this.type = type;
   }

   public long getNextCheckMillis(Date now)
   {
		return getNextCheckDate(now).getTime();
   }

   public Date getNextCheckDate(Date now)
   {
		this.setTime(now);

		switch(type)
		{
		   case DailyRollingFileAppender.TOP_OF_MINUTE:
		      this.set(Calendar.SECOND, 0);
		      this.set(Calendar.MILLISECOND, 0);
		      this.add(Calendar.MINUTE, 1);
		      break;
		   case DailyRollingFileAppender.TOP_OF_HOUR:
		      this.set(Calendar.MINUTE, 0);
		      this.set(Calendar.SECOND, 0);
		      this.set(Calendar.MILLISECOND, 0);
		      this.add(Calendar.HOUR_OF_DAY, 1);
		      break;
		   case DailyRollingFileAppender.HALF_DAY:
		      this.set(Calendar.MINUTE, 0);
		      this.set(Calendar.SECOND, 0);
		      this.set(Calendar.MILLISECOND, 0);
		      int hour = get(Calendar.HOUR_OF_DAY);
		      if(hour < 12)
		      {
		         this.set(Calendar.HOUR_OF_DAY, 12);
		      }
		      else
		      {
		         this.set(Calendar.HOUR_OF_DAY, 0);
		         this.add(Calendar.DAY_OF_MONTH, 1);
		      }
		      break;
		   case DailyRollingFileAppender.TOP_OF_DAY:
		      this.set(Calendar.HOUR_OF_DAY, 0);
		      this.set(Calendar.MINUTE, 0);
		      this.set(Calendar.SECOND, 0);
		      this.set(Calendar.MILLISECOND, 0);
		      this.add(Calendar.DATE, 1);
		      break;
		   case DailyRollingFileAppender.TOP_OF_WEEK:
		      this.set(Calendar.DAY_OF_WEEK, getFirstDayOfWeek());
		      this.set(Calendar.HOUR_OF_DAY, 0);
		      this.set(Calendar.SECOND, 0);
		      this.set(Calendar.MILLISECOND, 0);
		      this.add(Calendar.WEEK_OF_YEAR, 1);
		      break;
		   case DailyRollingFileAppender.TOP_OF_MONTH:
		      this.set(Calendar.DATE, 1);
		      this.set(Calendar.HOUR_OF_DAY, 0);
		      this.set(Calendar.SECOND, 0);
		      this.set(Calendar.MILLISECOND, 0);
		      this.add(Calendar.MONTH, 1);
		      break;
		   default:
		      throw new IllegalStateException("Unknown periodicity type.");
		}
		return getTime();
   }
}