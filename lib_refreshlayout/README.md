# 下拉刷新和轮播图文档

## 1.下拉刷新
Github地址：https://github.com/huxq17/XRefreshView

### 初始化头部
    HeaderPullAnim mHeader = new HeaderPullAnim(getContext());//此处头部是自定义头部（首页兜兜形象)
    
### 设置下拉刷新(com.andview.refreshview.XRefreshView)
    binding.blHomeRefresh.setCustomHeaderView(mHeader);
    binding.blHomeRefresh.setMoveForHorizontal(true);//支持水平滑动
    binding.blHomeRefresh.setPinnedTime(200);//设置成功展示时间间隔
    binding.blHomeRefresh.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh(boolean isPullDown) {//下拉
                BhResVM.getInstance().getRecomenndRes();
            }
            
            @Override
            public void void onLoadMore(boolean isSilence){//下拉
            
            }
        });
    binding.blHomeRefresh.startRefresh();//代码开始下拉刷新
    binding.blHomeRefresh.startLoadMore();//下拉
    
### RecyclerView底部静默加载
    binding.blHomeContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if(recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    boolean isBottom = !isLoadingMore && (!recyclerView.canScrollVertically(1) ||
                            (layoutManager.findLastVisibleItemPosition() >= (adapter.getItemCount()-16)) );
                    if (isBottom) {//是否到滑动到底部
                        getGlobalRecommend();
                    }
                }
            }
        });
