package com.bancoazteca.dogsjetpack.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavArgs;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class DetailFragmentArgs implements NavArgs {
  private final HashMap arguments = new HashMap();

  private DetailFragmentArgs() {
  }

  private DetailFragmentArgs(HashMap argumentsMap) {
    this.arguments.putAll(argumentsMap);
  }

  @NonNull
  @SuppressWarnings("unchecked")
  public static DetailFragmentArgs fromBundle(@NonNull Bundle bundle) {
    DetailFragmentArgs __result = new DetailFragmentArgs();
    bundle.setClassLoader(DetailFragmentArgs.class.getClassLoader());
    if (bundle.containsKey("dogUuid")) {
      int dogUuid;
      dogUuid = bundle.getInt("dogUuid");
      __result.arguments.put("dogUuid", dogUuid);
    }
    return __result;
  }

  @SuppressWarnings("unchecked")
  public int getDogUuid() {
    return (int) arguments.get("dogUuid");
  }

  @SuppressWarnings("unchecked")
  @NonNull
  public Bundle toBundle() {
    Bundle __result = new Bundle();
    if (arguments.containsKey("dogUuid")) {
      int dogUuid = (int) arguments.get("dogUuid");
      __result.putInt("dogUuid", dogUuid);
    }
    return __result;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object) {
        return true;
    }
    if (object == null || getClass() != object.getClass()) {
        return false;
    }
    DetailFragmentArgs that = (DetailFragmentArgs) object;
    if (arguments.containsKey("dogUuid") != that.arguments.containsKey("dogUuid")) {
      return false;
    }
    if (getDogUuid() != that.getDogUuid()) {
      return false;
    }
    return true;
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = 31 * result + getDogUuid();
    return result;
  }

  @Override
  public String toString() {
    return "DetailFragmentArgs{"
        + "dogUuid=" + getDogUuid()
        + "}";
  }

  public static class Builder {
    private final HashMap arguments = new HashMap();

    public Builder(DetailFragmentArgs original) {
      this.arguments.putAll(original.arguments);
    }

    public Builder() {
    }

    @NonNull
    public DetailFragmentArgs build() {
      DetailFragmentArgs result = new DetailFragmentArgs(arguments);
      return result;
    }

    @NonNull
    public Builder setDogUuid(int dogUuid) {
      this.arguments.put("dogUuid", dogUuid);
      return this;
    }

    @SuppressWarnings("unchecked")
    public int getDogUuid() {
      return (int) arguments.get("dogUuid");
    }
  }
}
