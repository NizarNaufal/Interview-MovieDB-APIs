package com.virmana.flix.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.virmana.flix.ui.player.CustomPlayerViewModel;

public abstract class FragmentPlayerBinding extends ViewDataBinding {
  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final RelativeLayout relativeLayoutSubtitlesDialog;

  @NonNull
  public final SimpleExoPlayerView videoView;

  @Bindable
  protected CustomPlayerViewModel mPlayerVm;

  protected FragmentPlayerBinding(DataBindingComponent _bindingComponent, View _root,
      int _localFieldCount, ProgressBar progressBar, RelativeLayout relativeLayoutSubtitlesDialog,
      SimpleExoPlayerView videoView) {
    super(_bindingComponent, _root, _localFieldCount);
    this.progressBar = progressBar;
    this.relativeLayoutSubtitlesDialog = relativeLayoutSubtitlesDialog;
    this.videoView = videoView;
  }

  public abstract void setPlayerVm(@Nullable CustomPlayerViewModel playerVm);

  @Nullable
  public CustomPlayerViewModel getPlayerVm() {
    return mPlayerVm;
  }

  @NonNull
  public static FragmentPlayerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentPlayerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentPlayerBinding>inflate(inflater, com.virmana.flix.R.layout.fragment_player, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentPlayerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  @NonNull
  public static FragmentPlayerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable DataBindingComponent component) {
    return DataBindingUtil.<FragmentPlayerBinding>inflate(inflater, com.virmana.flix.R.layout.fragment_player, null, false, component);
  }

  public static FragmentPlayerBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  public static FragmentPlayerBinding bind(@NonNull View view,
      @Nullable DataBindingComponent component) {
    return (FragmentPlayerBinding)bind(component, view, com.virmana.flix.R.layout.fragment_player);
  }
}
