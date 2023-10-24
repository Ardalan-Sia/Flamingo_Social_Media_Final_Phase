package request;

import response.Response;

public class GotoExplorer extends Request{
    @Override
    public Response visit(RequestVisitor requestVisitor) {
        return requestVisitor.gotoExplorerTweets();
    }
}
