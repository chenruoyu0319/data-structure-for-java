/**
 * @Author: Chen ruoyu
 * @Description: 设计一个浏览器的前进和后退功能
 * 注意: 点了一个新的页面，点后退只能回到主页，回不到旧的记录
 * @Date Created in:  2021-11-14 21:56
 * @Modified By:
 */
public class BrowserForwardAndBack {

    ArrayStack<String> forwardStack = new ArrayStack<>(20);
    ArrayStack<String> backStack = new ArrayStack<>(20);

    public void button(Browser browser) {
        if (browser.getFlag() == 0) {
            if (browser.getPage() == 0) {
                backStack.pop();

            } else if (browser.getPage() == 1) {
                while (backStack.size() > 0) {
                    backStack.pop();
                }
            }
            forwardStack.push(browser.getHandler());
        } else if (browser.getFlag() == 1) {
            forwardStack.pop();
            backStack.push(browser.getHandler());
        }
    }
}

class Browser {

    /**
     * 0: forward
     * 1: back
     */
    private int flag;

    /**
     * 0: 旧页面
     * 1: 新页面
     */
    private int page;

    /**
     * 浏览器句柄
     */
    private String handler;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }
}
