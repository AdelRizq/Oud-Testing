describe('Home Tests', function (){

    it('Check Buttons and URLS',function(){
       cy.HomeOverView()
    })
    it('Check All the playlist in the Catigories ',function(){
        cy.HomePlaylists()
    })
    it('Check The Playlist catigories',function(){
    cy.CheckCategories()
    })
    it('Check Search bar ',function(){
       cy.CheckSearchBar()
    })
    it('CheckUpgradeButtonResponsiveness',function(){
        cy.CheckUpgradeButtonResponsiveness()
    })
    it('CheckAccountButtonResponsiveness',function(){
        cy.CheckAccountButtonResponsiveness()
    })
    it('CheckLogoutButtonResponsiveness',function(){
        cy.CheckLogoutButtonResponsiveness()
    })
    it('Check the Liked Songs ',function(){
        cy.CheckLikedSongs()
    })
    it('check the routes of LikedSongs',function(){
       cy.CheckRoutesinLikedSongs()
    })
    it('Check Back and Forward Buttons ',function(){
       cy.CheckBackForwardButtons()
     })


})
// if you clicked on the upgrade button it will take you to the home adding /? to the route .. 
// but if you clicked on the Upgrade word it will take you to the sign in  
// if you go the the liked songs page 
// if you clicked on the the icons in the liked songs 
// it will change its place 