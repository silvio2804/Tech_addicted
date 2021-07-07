package Model.discount;

import Model.storage.FormExtractor;

import javax.servlet.http.HttpServletRequest;

public class DiscountFormExtractor implements FormExtractor<Discount> {
    @Override
    public Discount extract(HttpServletRequest request, boolean update) {
        Discount discount = new Discount();
        if(update){
            discount.setDiscountId(Integer.parseInt(request.getParameter("discountId")));
        }
        discount.setDiscountName(request.getParameter("discountName"));
        discount.setPercentage(Integer.parseInt(request.getParameter("percentage")));
        return discount;
    }
}
