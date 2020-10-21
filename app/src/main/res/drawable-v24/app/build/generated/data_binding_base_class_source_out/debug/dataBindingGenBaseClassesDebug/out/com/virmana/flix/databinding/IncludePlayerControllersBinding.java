package com.virmana.flix.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.virmana.flix.ui.player.CustomPlayerViewModel;

public abstract class IncludePlayerControllersBinding extends ViewDataBinding {
  @NonNull
  public final ImageView mediaPlayPause;

  @NonNull
  public final TextView mediaSubtitle;

  @Bindable
  protected CustomPlayerViewModel mPlayerVm;

  protected IncludePlayerControllersBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ImageView mediaPlayPause, TextView mediaSubtitle) {
    super(_bindingComponent, _root, _localFieldCount);
    this.mediaPlayPause = mediaPlayPause;
    this.mediaSubtitle = mediaSubtitle;
  }

  public abstract void setPlayerVm(@Nullable CustomPlayerViewModel playerVm);

  @Nullable
  public CustomPlayerViewModel getPlayerVm() {
    return mPlayerVm;
  }

  @NonNull
  public static IncludePlayerControllersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static IncludePlayerControllersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<IncludePlayerControllersBinding>inflate(inflater, com.virmana.flix.R.layout.include_player_controllers, root, attachToRoot, component);
  }

  @NonNull
  public static IncludePlayerControllersBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static IncludePlayerControllersBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<IncludePlayerControllersBinding>inflate(inflater, com.virmana.flix.R.layout.include_player_controllers, null, false, component);
  }

  public static IncludePlayerControllersBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static IncludePlayerControllersBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (IncludePlayerControllersBinding)bind(component, view, com.virmana.flix.R.layout.include_player_controllers);
  }
}
