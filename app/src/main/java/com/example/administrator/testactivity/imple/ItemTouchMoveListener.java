package com.example.administrator.testactivity.imple;

public interface ItemTouchMoveListener {
    /**
     * 当拖拽的时候回调
     * @param fromPosition
     * @param toPosition
     * @return
     */
    boolean onItemMove(int fromPosition, int toPosition);

    /**
     * 当条目被删除的时候回调这个接口
     * @param position
     * @return
     */
    boolean onItemRemove(int position);
}
