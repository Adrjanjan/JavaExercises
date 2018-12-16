package Lab7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class AdminUnitQuery {
    AdminUnitList src;
    Predicate<AdminUnit> p = adminUnit -> true;
    Comparator<AdminUnit> cmp;
    int limit = Integer.MAX_VALUE;
    int offset = 0;

    /**
     * Ustawia listę jako przetwarzane źródło
     *
     * @param src source of AdminUnits
     * @return this
     */
    AdminUnitQuery selectFrom(AdminUnitList src) {
        this.src = src;
        return this;
    }

    /**
     * @param pred - ustawia predykat p
     * @return this
     */
    AdminUnitQuery where(Predicate<AdminUnit> pred) {
        p = pred;
        return this;
    }

    /**
     * Wykonuje operację p = p and pred
     *
     * @param pred
     * @return this
     */
    AdminUnitQuery and(Predicate<AdminUnit> pred) {
        p = p.and(pred);
        return this;
    }

    /**
     * Wykonuje operację p = p or pred
     *
     * @param pred
     * @return this
     */
    AdminUnitQuery or(Predicate<AdminUnit> pred) {
        p = p.or(pred);
        return this;
    }

    /**
     * Ustawia komparator cmp
     *
     * @param cmp
     * @return this
     */
    AdminUnitQuery sort(Comparator<AdminUnit> cmp) {
        this.cmp = cmp;
        return this;
    }

    /**
     * Ustawia limit
     *
     * @param limit
     * @return this
     */
    AdminUnitQuery limit(int limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Ustawia offset
     *
     * @param offset
     * @return this
     */
    AdminUnitQuery offset(int offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Wykonuje zapytanie i zwraca wynikową listę
     *
     * @return przefiltrowana i opcjonalnie posortowana lista (uwzględniamy także offset/limit)
     */
    AdminUnitList execute() {
        List<AdminUnit> list = new ArrayList<>();
        src.units.sort(cmp);
        for (AdminUnit unit : src.units) {
            if (offset-- > 0) continue;
            if (p.test(unit)) {
                list.add(unit);
                if (limit-- == 0) break;
            }
        }
        src.units = list;
        return src;
    }
}