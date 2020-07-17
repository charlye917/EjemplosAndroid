package com.bancoazteca.dogsjetpack.view;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.bancoazteca.dogsjetpack.R;

public class DetailFragmentDirections {
  private DetailFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionListFragment() {
    return new ActionOnlyNavDirections(R.id.actionListFragment);
  }
}
