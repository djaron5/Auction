<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as login>

<@c.page>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-8 order-md-1">
                <h4 class="col-4">${auction.auctionName}</h4>
                <div class="media mt-3 row">
                    <div class="col-md-3">
                        <img src="/img/<#if auction.filename?has_content>${auction.filename}<#else>default.jpg</#if>"
                             class="img-fluid" style="max-width: 150px">
                    </div>
                    <#if auction.description??>
                        <div class="media-body ml-4 col-md-9">
                            <p class="text-justify">${auction.description}</p>
                        </div>
                    </#if>
                </div>
            </div>
            <div class="col-md-4 order-md-2 mb-4">
                <h4>Beginning price: ${auction.beginningPrice}$</h4>
                <label class="font-weight-bold mt-2">Status: <label class="<#if auction.auctionStatus == "ASSIGNED">
                    text-info
                    <#else>
                     text-danger
                </#if>">${auction.auctionStatus}</label>
                </label>
                <h5 class="mt-3">Participants (${auction.participants?size})</h5>
                <ul class="list-group mb-3">
                    <#list auction.getParticipants() as participate>
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
                                ${participate.username}
                            </div>
                        </li>
                    </#list>
                </ul>
                <#if auction.participants?seq_contains(user)>
                    <a class="btn btn-danger" href="/auction/unsubscribe/${auction.id}">Unsubscribe</a>
                <#else>
                    <a class="btn btn-info" href="/auction/subscribe/${auction.id}">Participate</a>
                </#if>
                <div class="mt-4">
                    Starts at ${beginningAuctionTime}
                </div>
            </div>
        </div>
    </div>
</@c.page>