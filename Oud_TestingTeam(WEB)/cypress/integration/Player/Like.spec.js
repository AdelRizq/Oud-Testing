describe('Like', () => {
    before('vistit Oud', () => {
        cy.fixture('Player/playerIDs.json').then((data) => {
            self.playerIds = data;
        })
        cy.visit('/').then(() => {
            cy.wait(1);

        });
    });

    it('like Song', () => {
        cy.wait(1000);
        // it has no uniqe id so i had to use xpath
        cy.xpath('//*[@id="root"]/div/div[4]/div/div[3]/div/button[1]/img').should('have.attr', 'alt', 'Love');
        cy.get(`[data-testid='${self.playerIds.Like}']`).click().then(() => {
            cy.xpath('//*[@id="root"]/div/div[4]/div/div[3]/div/button[1]/img').should('have.attr', 'alt', 'Loved');
        });
    });

    it('see if it liked song is in liked Songs', () => {
        cy.get('a').contains('Liked Songs').click().then(() => {
            cy.url().should('contains', 'likedSongs');
            cy.get(`[data-testid='${self.playerIds.trackName}']`).then((trackName) => {
                let Name = trackName.text();
                cy.get(`[data-testid='${self.playerIds.likedTrackName}']`).should('contain.text', Name);
            });

        })

    })
});