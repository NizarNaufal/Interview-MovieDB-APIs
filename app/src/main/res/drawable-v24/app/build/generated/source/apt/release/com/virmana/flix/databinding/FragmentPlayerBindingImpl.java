package com.virmana.flix.databinding;
import com.virmana.flix.R;
import com.virmana.flix.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentPlayerBindingImpl extends FragmentPlayerBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.video_view, 3);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentPlayerBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private FragmentPlayerBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.ProgressBar) bindings[1]
            , (android.widget.RelativeLayout) bindings[2]
            , (com.google.android.exoplayer2.ui.SimpleExoPlayerView) bindings[3]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.progressBar.setTag(null);
        this.relativeLayoutSubtitlesDialog.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
        else if (fieldId == BR.loaidingNow) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
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
        com.virmana.flix.ui.player.CustomPlayerViewModel playerVm = mPlayerVm;
        int playerVmIsLoaidingNowViewVISIBLEViewGONE = 0;
        boolean playerVmIsLoaidingNow = false;

        if ((dirtyFlags & 0x7L) != 0) {



                if (playerVm != null) {
                    // read playerVm.isLoaidingNow
                    playerVmIsLoaidingNow = playerVm.isLoaidingNow();
                }
            if((dirtyFlags & 0x7L) != 0) {
                if(playerVmIsLoaidingNow) {
                        dirtyFlags |= 0x10L;
                }
                else {
                        dirtyFlags |= 0x8L;
                }
            }


                // read playerVm.isLoaidingNow ? View.VISIBLE : View.GONE
                playerVmIsLoaidingNowViewVISIBLEViewGONE = ((playerVmIsLoaidingNow) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
        }
        // batch finished
        if ((dirtyFlags & 0x7L) != 0) {
            // api target 1

            this.progressBar.setVisibility(playerVmIsLoaidingNowViewVISIBLEViewGONE);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): playerVm
        flag 1 (0x2L): playerVm.isLoaidingNow
        flag 2 (0x3L): null
        flag 3 (0x4L): playerVm.isLoaidingNow ? View.VISIBLE : View.GONE
        flag 4 (0x5L): playerVm.isLoaidingNow ? View.VISIBLE : View.GONE
    flag mapping end*/
    //end
}