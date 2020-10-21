package com.virmana.flix.databinding;
import com.virmana.flix.R;
import com.virmana.flix.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class IncludePlayerControllersBindingImpl extends IncludePlayerControllersBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.media_subtitle, 2);
    }
    // views
    @NonNull
    private final android.widget.FrameLayout mboundView0;
    // variables
    // values
    // listeners
    private OnClickListenerImpl mPlayerVmOnPlayPauseClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mPlayerVmOnPlayerClickedAndroidViewViewOnClickListener;
    // Inverse Binding Event Handlers

    public IncludePlayerControllersBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private IncludePlayerControllersBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            );
        this.mboundView0 = (android.widget.FrameLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mediaPlayPause.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.playerVm == variableId) {
            setPlayerVm((com.virmana.flix.ui.player.CustomPlayerViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPlayerVm(@Nullable com.virmana.flix.ui.player.CustomPlayerViewModel PlayerVm) {
        updateRegistration(0, PlayerVm);
        this.mPlayerVm = PlayerVm;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.playerVm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangePlayerVm((com.virmana.flix.ui.player.CustomPlayerViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangePlayerVm(com.virmana.flix.ui.player.CustomPlayerViewModel PlayerVm, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        else if (fieldId == BR.loadingComplete) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        else if (fieldId == BR.playbackImageRes) {
            synchronized(this) {
                    mDirtyFlags |= 0x4L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        boolean playerVmLoadingComplete = false;
        int playerVmPlaybackImageRes = 0;
        com.virmana.flix.ui.player.CustomPlayerViewModel playerVm = mPlayerVm;
        android.view.View.OnClickListener playerVmOnPlayPauseClickedAndroidViewViewOnClickListener = null;
        android.view.View.OnClickListener playerVmOnPlayerClickedAndroidViewViewOnClickListener = null;
        int playerVmLoadingCompleteViewVISIBLEViewGONE = 0;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xbL) != 0) {

                    if (playerVm != null) {
                        // read playerVm.loadingComplete
                        playerVmLoadingComplete = playerVm.getLoadingComplete();
                    }
                if((dirtyFlags & 0xbL) != 0) {
                    if(playerVmLoadingComplete) {
                            dirtyFlags |= 0x20L;
                    }
                    else {
                            dirtyFlags |= 0x10L;
                    }
                }


                    // read playerVm.loadingComplete ? View.VISIBLE : View.GONE
                    playerVmLoadingCompleteViewVISIBLEViewGONE = ((playerVmLoadingComplete) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
            }
            if ((dirtyFlags & 0xdL) != 0) {

                    if (playerVm != null) {
                        // read playerVm.playbackImageRes
                        playerVmPlaybackImageRes = playerVm.getPlaybackImageRes();
                    }
            }
            if ((dirtyFlags & 0x9L) != 0) {

                    if (playerVm != null) {
                        // read playerVm::onPlayPauseClicked
                        playerVmOnPlayPauseClickedAndroidViewViewOnClickListener = (((mPlayerVmOnPlayPauseClickedAndroidViewViewOnClickListener == null) ? (mPlayerVmOnPlayPauseClickedAndroidViewViewOnClickListener = new OnClickListenerImpl()) : mPlayerVmOnPlayPauseClickedAndroidViewViewOnClickListener).setValue(playerVm));
                        // read playerVm::onPlayerClicked
                        playerVmOnPlayerClickedAndroidViewViewOnClickListener = (((mPlayerVmOnPlayerClickedAndroidViewViewOnClickListener == null) ? (mPlayerVmOnPlayerClickedAndroidViewViewOnClickListener = new OnClickListenerImpl1()) : mPlayerVmOnPlayerClickedAndroidViewViewOnClickListener).setValue(playerVm));
                    }
            }
        }
        // batch finished
        if ((dirtyFlags & 0x9L) != 0) {
            // api target 1

            this.mboundView0.setOnClickListener(playerVmOnPlayerClickedAndroidViewViewOnClickListener);
            this.mediaPlayPause.setOnClickListener(playerVmOnPlayPauseClickedAndroidViewViewOnClickListener);
        }
        if ((dirtyFlags & 0xbL) != 0) {
            // api target 1

            this.mediaPlayPause.setVisibility(playerVmLoadingCompleteViewVISIBLEViewGONE);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            this.mediaPlayPause.setImageResource(playerVmPlaybackImageRes);
        }
    }
    // Listener Stub Implementations
    public static class OnClickListenerImpl implements android.view.View.OnClickListener{
        private com.virmana.flix.ui.player.CustomPlayerViewModel value;
        public OnClickListenerImpl setValue(com.virmana.flix.ui.player.CustomPlayerViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onPlayPauseClicked(arg0); 
        }
    }
    public static class OnClickListenerImpl1 implements android.view.View.OnClickListener{
        private com.virmana.flix.ui.player.CustomPlayerViewModel value;
        public OnClickListenerImpl1 setValue(com.virmana.flix.ui.player.CustomPlayerViewModel value) {
            this.value = value;
            return value == null ? null : this;
        }
        @Override
        public void onClick(android.view.View arg0) {
            this.value.onPlayerClicked(arg0); 
        }
    }
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): playerVm
        flag 1 (0x2L): playerVm.loadingComplete
        flag 2 (0x3L): playerVm.playbackImageRes
        flag 3 (0x4L): null
        flag 4 (0x5L): playerVm.loadingComplete ? View.VISIBLE : View.GONE
        flag 5 (0x6L): playerVm.loadingComplete ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}