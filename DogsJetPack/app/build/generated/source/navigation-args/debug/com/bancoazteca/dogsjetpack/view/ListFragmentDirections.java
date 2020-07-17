package com.bancoazteca.dogsjetpack.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.bancoazteca.dogsjetpack.R;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ListFragmentDirections {
  private ListFragmentDirections() {
  }

  @NonNull
  public static ActionDetailFragment actionDetailFragment() {
    return new ActionDetailFragment();
  }

  public static class ActionDetailFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    private ActionDetailFragment() {
    }

    @NonNull
    public ActionDetailFragment setDogUuid(int dogUuid) {
      this.arguments.put("dogUuid", dogUuid);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("dogUuid")) {
        int dogUuid = (int) arguments.get("dogUuid");
        __result.putInt("dogUuid", dogUuid);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.actionDetailFragment;
    }

    @SuppressWarnings("unchecked")
    public int getDogUuid() {
      return (int) arguments.get("dogUuid");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionDetailFragment that = (ActionDetailFragment) object;
      if (arguments.containsKey("dogUuid") != that.arguments.containsKey("dogUuid")) {
        return false;
      }
      if (getDogUuid() != that.getDogUuid()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + getDogUuid();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionDetailFragment(actionId=" + getActionId() + "){"
          + "dogUuid=" + getDogUuid()
          + "}";
    }
  }
}
