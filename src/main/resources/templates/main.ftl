<#import "parts/common.ftl" as c>

<@c.page>
    <a href="/createAuction">Create own auction</a>

    <div class="columns">
        <#list auctions as auction>
            <div class="auction my-3 container">
                <div class="row justify-content-center">
                    <div class="col-4">
                    <span class="m-1 col-sm">
                        Auction: <a href="/auction/${auction.getId()}">${auction.auctionName}</a>
                    </span>
                    </div>
                    <div class="row-4">
                        <i class="m-1 col-sm" style="text-align: right">
                            Starting price: ${auction.beginningPrice}
                        </i>
                    </div>
                </div>
                <div class="row justify-content-start">
                    <#if auction.filename??>
                        <div class="col-3">
                            <img src="/img/${auction.filename}">
                        </div>
                    </#if>
                    <#if auction.description??>
                        <div class="col-5">
                            <label>${auction.description}</label>
                        </div>
                    </#if>
                </div>
                <div class="row justify-content-between">
                    <div class="col-4">
                        Starts at: ${auction.beginningAuctionTime}
                    </div>
                    <div class="col-4">
                        <strong>Author: ${auction.owner.username}</strong>
                    </div>
                </div>
            </div>
        <#else>
            <p class="font-italic">
                No planned auctions
            </p>
        </#list>
    </div>
</@c.page>