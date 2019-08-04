<#import "parts/common.ftl" as c>

<@c.page>
    <div class="form-group">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group row">
                <label for="inputAuctionName" class="col-sm-2 col-form-label">Auction name</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control ${(auctionNameError??)?string('is-invalid', '')}"
                           id="inputAuctionName" name="auctionName" placeholder="Auction name">
                    <#if auctionNameError??>
                        <div class="invalid-feedback">
                            ${auctionNameError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label for="beginningPrice" class="col-sm-2 col-form-label">Beginning price</label>
                <div class="col-sm-6">
                    <input type="number" name="beginningPrice" class="form-control ${(beginningPriceError??)?string('is-invalid', '')}" id="beginningPrice"
                           placeholder="Beginning price" value="100">
                    <#if beginningPriceError??>
                        <div class="invalid-feedback">
                            ${beginningPriceError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputDescription" class="col-sm-2 col-form-label">Description</label>
                <div class="col-sm-6">
                    <textarea style="resize: none" name="description" class="form-control ${(beginningPriceError??)?string('is-invalid', '')}" id="inputDescription"
                              placeholder="Description"></textarea>
                    <#if descriptionError??>
                        <div class="invalid-feedback">
                            ${descriptionError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputFile" class="col-sm-2 col-form-label">File</label>
                <div class="col-sm-6">
                    <div class="custom-file">
                        <input type="file" name="file" id="inputFile">
                        <label class="custom-file-label" for="inputFile">Choose file</label>
                    </div>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputDate" class="col-sm-2 col-form-label">Enter date:</label>
                <div class="col-sm-6">
                    <input type="datetime-local" name="beginningAuctionTime"
                           class="form-control ${(beginningAuctionTimeError??)?string('is-invalid', '')}" id="inputDate">
                    <#if beginningAuctionTimeError??>
                        <div class="invalid-feedback">
                            ${beginningAuctionTimeError}
                        </div>
                    </#if>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div>
                <button class="btn btn-primary mt-2" type="submit">Add auction</button>
            </div>
        </form>
    </div>
</@c.page>