package razerdp.basepopup;

import android.content.Context;
import android.util.Pair;
import android.view.View;

/**
 * Created by 大灯泡 on 2019/11/24.
 * 延迟初始化的BasePopup，该PopupWindow将会在您调用show方法的时候才会进行初始化
 */
public abstract class BaseLazyPopupWindow extends BasePopupWindow {
    private boolean initImmediately = false;
    private Pair<Integer, Integer> sizeCached;

    public BaseLazyPopupWindow(Context context) {
        this(context, 0,0);
    }

    public BaseLazyPopupWindow(Context context, int width, int height) {
        super(context, width, height);
        sizeCached = Pair.create(width, height);
    }

    public final void initImmediately() {
        initImmediately = true;
        if (sizeCached != null) {
            initView(sizeCached.first, sizeCached.second);
            sizeCached = null;
        } else {
            initView(0, 0);
        }
    }

    @Override
    void initView(int width, int height) {
        if (!initImmediately) return;
        super.initView(width, height);
    }

    @Override
    void tryToShowPopup(View v, boolean positionMode) {
        if (!initImmediately) {
            initImmediately();
        }
        super.tryToShowPopup(v, positionMode);
    }
}
