package pansong291.devlphelper;

import pansong291.devlphelper.CrashActivity;
import pansong291.crash.CrashApplication;

public class MyApplication extends CrashApplication
{
 @Override
 public Class<?> getPackageActivity()
 {
  setShouldShowDeviceInfo(false);
  return CrashActivity.class;
 }

}
