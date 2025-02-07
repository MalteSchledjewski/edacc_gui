package edacc.parametergrapheditor;

import edacc.parameterspace.domain.Domain;

/**
 *
 * @author simon
 */
public interface IDomainPanel {
    public Domain getDomain() throws InvalidDomainException;
    public void setDomain(Domain orDomain, Domain andDomain) throws InvalidDomainException;
}
