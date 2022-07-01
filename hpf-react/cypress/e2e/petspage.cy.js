const plinko = Cypress.env("plinko")

describe('renders the pets page', () => {
    beforeEach(() => {
        cy.visit('/pets/categories/cats')
    })

    it('renders correctly', () => {
        cy.get('#container')
    })

    it("allows the search bar to be used", () => {
        cy.get('#search').type('domestic')
        cy.findAllByText('Dusk').should('not.exist')
    })

    it("allows the sort dropdown to be used", () => {
        cy.get('#sort').select('nameDesc')
    })

    it("allows the search bar and the sort dropdown to be used", () => {
        cy.get('#search').type('domestic')
        cy.get('#sort').select('nameDesc')
        cy.findAllByText('Dusk').should('not.exist')
    });

    it("searches, sorts, and routes to a log in page", () => {
        cy.get('#search').type('domestic')
        cy.get('#sort').select('nameDesc')
        cy.findAllByText(plinko).should('exist')
        cy.findAllByText(plinko).click()
        cy.url().should('include', 'logIn')
    })
})