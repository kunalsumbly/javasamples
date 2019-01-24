

/**
 * Backing bean for support config vehicle data for contract
 * @author kusu
 *
 */
@Named("contractQuoteDetail")
@ViewScoped
public class QuoteDetail extends FacesBean {
	private static final long serialVersionUID = -3107448362134450750L;

	private IMWSRequest request;
	private MWLeaseService service;
	private MWConfigVehicle vehicle;

	private int quoteId;
	private boolean isCompleted = false;
	private boolean isPricingEditable = true;

	private HashMap<String, Boolean> allowedactions;
	private String additionalQuoteNote;

	private Double dealerDeliveryCostPrice;
	private Double initialRegistrationCostPrice;
	private Double initialCTPPremiumCostPrice;
	private Double initialCTPPremiumCostPriceGST;
	private Double initialCTPOtherCostPrice;
	private Double plateFeeCostPrice;
	private Double totalIncGSTAndOtherDeliveryCosts;

	public QuoteDetail() throws Exception {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		try {
			String contractIdParam = params.get("contractid");
			ContractManager cm = new ContractManager(getWA().getMwsProxy());
			MWContract mwContract = new MWContract(cm.getContract(getWS().getUser(),
					Integer.valueOf(contractIdParam), LSComponents.NONE, false, false));
			request = mwContract.getMWSRequest();
			service = new MWLeaseService(mwContract.getMWSRequest().getMWSLeaseService());
			quoteId = service.getQuoteId();
		} catch (Exception ex) {
			throw new PermissionDeniedException();
		}
		request.getMWSLeaseService().getStatusRepr();
		if (request.getMWSLeaseService().getMWSVehicle() != null) {
			// Some retail products a

	public Double getSubTotalGST() throws MWSException {
		Double totalIncGST = getTotalIncGST();
		Double subTotal = getSubTotal();

		return totalIncGST - subTotal;
	}

	public Double getTotalIncGST() throws MWSException {
		Double totalIncGST = new Double(0);
		totalIncGST = NumberUtils.addVat(getSubTotal(), new Integer(WebConstants.DEALER_VEHICLE_PRICE_VAT_CODE_ENUM_ID));
		return totalIncGST;
	}

	public Double getInitialRegistrationCostPrice() {
		initialRegistrationCostPrice = vehicle.getInitialRegistrationCostPrice();
		return initialRegistrationCostPrice;
	}

	public void setInitialRegistrationCostPrice(Double initialRegistrationCostPrice) {
		this.initialRegistrationCostPrice = initialRegistrationCostPrice;
		vehicle.setInitialRegistrationCostPrice(initialRegistrationCostPrice);
		return;
	}

	public Double getDealerDeliveryCostPrice() {
		dealerDeliveryCostPrice = vehicle.getDealerDeliveryCostPrice();
		return dealerDeliveryCostPrice;
	}

	public void setDealerDeliveryCostPrice(Double dealerDeliveryCost) {
		this.dealerDeliveryCostPrice = dealerDeliveryCost;
		vehicle.setDealerDeliveryCostPrice(dealerDeliveryCost);
		return;
	}

	public Double getLuxuryCarTaxCostPrice() {
		return vehicle.getLuxuryCarTaxCostPrice();
	}

	public Double getNonReclaimableGSTCostPrice() {
		return vehicle.getNonReclaimableGSTCostPrice();
	}

	public Double getPurchaseStampDutyCostPrice() {
		return vehicle.getPurchaseStampDutyCostPrice();
	}

	public Double getNonReclaimableGSTCorrectionCostPrice() {
		return vehicle.getNonReclaimableGSTCorrectionCostPrice();
	}

	public Double getInitialCTPPremiumCostPrice() {
		initialCTPPremiumCostPrice = vehicle.getInitialCTPPremiumCostPrice();
		return initialCTPPremiumCostPrice;
	}

	public Double getInitialCTPPremiumCostPriceGST() {
		initialCTPPremiumCostPriceGST = NumberUtils.addVat(getInitialCTPPremiumCostPrice(),
				new Integer(WebConstants.DEALER_VEHICLE_PRICE_VAT_CODE_ENUM_ID)) - getInitialCTPPremiumCostPrice();
		return initialCTPPremiumCostPriceGST;
	}

	public void setInitialCTPPremiumCostPrice(Double initialCTPPremium) {
		this.initialCTPPremiumCostPrice = initialCTPPremiumCostPrice;
		vehicle.setInitialCTPPremiumCostPrice(initialCTPPremium);
		return;
	}

	public void setInitialCTPPremiumCostPriceGST(Double initialCTPPremiumCostPriceGST) {
		this.initialCTPPremiumCostPriceGST = initialCTPPremiumCostPriceGST;
		return;
	}

	public Double getInitialCTPOtherCostPrice() {
		initialCTPOtherCostPrice = vehicle.getInitialCTPOtherCostPrice();
		return initialCTPOtherCostPrice;
	}

	public void setInitialCTPOtherCostPrice(Double initialCTPOtherCostPrice) {
		this.initialCTPOtherCostPrice = initialCTPOtherCostPrice;
		vehicle.setInitialCTPOtherCostPrice(initialCTPOtherCostPrice);
		return;
	}

	public Double getPlateFeeCostPrice() {
		plateFeeCostPrice = vehicle.getPlateFeeCostPrice();
		return plateFeeCostPrice;
	}

	public void setPlateFeeCostPrice(Double plateFeeCostPrice) {
		this.plateFeeCostPrice = plateFeeCostPrice;
		vehicle.setPlateFeeCostPrice(plateFeeCostPrice);
		return;
	}

	public Double getTotalIncGSTAndOtherdeliverycosts() {
		totalIncGSTAndOtherDeliveryCosts = getTotalIncGST() + getInitialRegistrationCostPrice()
				+ getLuxuryCarTaxCostPrice() + getPurchaseStampDutyCostPrice()
				+ getInitialCTPPremiumCostPrice() + getInitialCTPPremiumCostPriceGST() + getInitialCTPOtherCostPrice()
				+ getPlateFeeCostPrice();

		return totalIncGSTAndOtherDeliveryCosts;
	}

	public void setTotalIncGSTAndOtherDeliveryCosts(Double totalIncGSTAndOtherDeliveryCosts) {
		this.totalIncGSTAndOtherDeliveryCosts = totalIncGSTAndOtherDeliveryCosts;
	}

	public String getPeriodicity() {
		String periodicityStr = "Cannot convert to periodicity";

		Map periodicityMap = WebConstants.DURATION_ENUM_MAP.get(service.getPeriodDurationUnitEnumId());
		if (periodicityMap != null && !periodicityMap.isEmpty()) {
			if (periodicityMap.get(service.getPeriodDuration().intValue()) != null) {
				periodicityStr = (String) periodicityMap.get(service.getPeriodDuration().intValue());
			}

		}

		return periodicityStr;
	}

}
