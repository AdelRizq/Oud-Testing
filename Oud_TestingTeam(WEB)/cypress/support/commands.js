// ***********************************************
// This example commands.js shows you how to
// create various custom commands and overwrite
// existing commands.
//
// For more comprehensive examples of custom
// commands please read more here:
// https://on.cypress.io/custom-commands
// ***********************************************
//
//
// -- This is a parent command --
// Cypress.Commands.add("login", (email, password) => { ... })
//
//
// -- This is a child command --
// Cypress.Commands.add("drag", { prevSubject: 'element'}, (subject, options) => { ... })
//
//
// -- This is a dual command --
// Cypress.Commands.add("dismiss", { prevSubject: 'optional'}, (subject, options) => { ... })
//
//
// -- This will overwrite an existing command --
// Cypress.Commands.overwrite("visit", (originalFn, url, options) => { ... })
before(() => {
    cy.fixture("Profile/Data.json").then((DataID)=>{
        self.Data=DataID
    })
    cy.fixture("Artist/Data.json").then((artistData)=>{

        self.artist=artistData
    })
    cy.fixture("pages/Data.json").then((pagesData)=>{

        self.pages=pagesData
    })
    cy.fixture("URLS.json").then((urlsData)=>{

        self.URL=urlsData
    })
  });
Cypress.Commands.add("Login",()=>
{

    cy.visit("/")
    cy.wait(3000)
    cy.contains("Log In").click()
    cy.wait(3000)
    cy.get(Data.emailbox).clear().type(Data.email)
    cy.get(Data.passwordbox).clear().type(Data.password)
    cy.get(Data.loginbutton).click({force:true})
    cy.wait(3000)


})
Cypress.Commands.add("toprofile",()=>{
    cy.wait(3000)
    cy.contains("Spotify").click()
    cy.scrollTo(0,500)
    cy.contains("Launch Web Player").click({force:true})
    Cypress.on('uncaught:exception', (err, runnable) => {
        // returning false here prevents Cypress from
        // failing the test
        return false
      })
    cy.get(Data.profileaccountbutton).click({force:true})
    cy.contains("Profile").click({force:true})
})
Cypress.Commands.add("createlist",()=>{

    cy.contains("Create Playlist").click({force:true})
    cy.get(Data.playlistbox).clear().type("testplaylist")
    cy.contains("CREATE").click({force:true})
    cy.wait(3000)
    cy.get(Data.profileaccountbutton).click({force:true})
    cy.contains("Profile").click({force:true})
    cy.reload()
    cy.wait(7000)
    cy.get('[class="main-view-container__scroll-node"]').scrollTo('bottom')
    cy.contains("testplaylist")

})
Cypress.Commands.add("addartist",()=>{

    cy.Login()
    cy.toprofile()
    cy.contains("Search").click({force:true})
    cy.get(Data.searchbox).clear().type("الحصري")
    cy.wait(3000)
    cy.contains("Al Sheikh Mahmoud Kh").click({force:true})
    cy.wait(3000)
    cy.contains("Follow").click({force:true})
    cy.wait(500)
    cy.contains("Saved to Your Library").should('be.visible') 
    cy.wait(3000)
    cy.contains("Your Library").click({force:true})
    cy.wait(1000)
    cy.contains("Artists").click({force:true})
    cy.reload()
    cy.wait(3000)
    cy.contains("Al Sheikh Mahmo")
    cy.go('back')
    cy.go('back')
    cy.wait(3000)
    cy.contains("Following").click({force:true})
    cy.wait(500)
    cy.contains("Removed from Your Library").should('be.visible') 
    cy.wait(3000)
    cy.get(artist.playbutton).click({force:true,multiple:true})
    cy.reload()
    cy.wait(3000)
    cy.contains("Popular").should('be.visible')
    cy.get('[class="main-view-container__scroll-node"]').scrollTo(0,500)
    cy.contains("Albums").should('be.visible')
    cy.get('[class="main-view-container__scroll-node"]').scrollTo(0,500)
    cy.contains("Singles and EPs").should('be.visible')
    cy.get('[class="main-view-container__scroll-node"]').scrollTo(0,1500)
    cy.contains("SHOW MORE").click({force:true})
    cy.get('[class="main-view-container__scroll-node"]')
    cy.url().should("eq",URL.artisturl)
 
    cy.contains("Related Artists").click({force:true})
    cy.url().should("eq",URL.artistrelated)
    cy.get('[class="main-view-container__scroll-node"]').scrollTo(0,300)
    cy.contains("Mohamed Siddiq")

    cy.get('[class="main-view-container__scroll-node"]')
    cy.contains("About").click({force:true})
    cy.url().should('eq',URL.artistabout)
    
    //cy.get(Data.profileaccountbutton).click({force:true})
    //cy.contains("Profile").click({force:true})
    //cy.reload()
    //cy.wait(3000)
})

Cypress.Commands.add("catpage",()=>{
    cy.Login()
    cy.wait(3000)
    cy.scrollTo('bottom')
    cy.wait(3000)
    cy.contains("Jobs").click({force:true})
    Cypress.on('uncaught:exception', (err, runnable) => {
        // returning false here prevents Cypress from
        // failing the test
        return false
      })
    cy.wait(3000)
    cy.contains("Join the band")
    //cy.url().should("eq",URL.jobsurl)
    cy.scrollTo(0,300)
    cy.contains("What's your passion?")
    cy.scrollTo(0,700)
    cy.contains("Featured jobs")
    cy.scrollTo(0,-400)
    cy.contains("All Categories").click({force:true})
    cy.url().should("eq",URL.categoriesurl)
    cy.wait(3000)
    cy.contains("Our Job Categories")
    cy.scrollTo(0,500)
    let cats=["Anchor","Brand & Creative","Content","Customer Service Experience","Data and Analytics","Design and User Experience","Engineering","Finance","Free Tier","Gimlet","Legal","Markets","Parcast","People","PR & Communications","Premium Tier","Product","Soundtrap",];

    for (let i   = 0 ; i <8;i++)
    {

        cy.contains(cats[i])

    }
    cy.scrollTo(0,500)
    for (let i = 8  ; i<16 ; i++){
        cy.contains(cats[i])
    }
    cy.scrollTo(0,500)
    cy.contains(cats[16])
    cy.contains(cats[17])
    cy.contains(cats[2]).click({force:true})
    cy.wait(3000)
    cy.url().should("eq",URL.contentofferurl)
    cy.contains("Content")
    cy.scrollTo(0,100)
    let jobs=["Studios Marketing Lead","Head of Marketplace Business","Audio Line Producer, Parcast","Senior Growth Marketing Manager, Soundbetter","Senior Growth Marketing Manager, Soundbetter","Technical Account Manager","Senior Data Scientist, Podcasts"];
    for (let i = 0 ; i<4 ; i++){
        cy.contains(jobs[0])
    }
    cy.contains("More jobs").click({force:true})
    cy.contains(jobs[4])
    cy.contains(jobs[5])
    cy.contains("Choose sub category").click({force:true})
    cy.contains("Content Business").click({force:true})
    cy.wait(3000)
    cy.contains(jobs[1])
    cy.contains(jobs[5])
})

Cypress.Commands.add("locationpage",()=>{
    
    cy.contains("Locations").click({force:true})
    cy.url().should("eq",URL.locationurl)
    cy.wait(3000)
    let locations =["Stockholm","New York","London","Tokyo","Seattle","Amsterdam","Berlin","Boston","Dubai","Cambridge","Gothenburg","Los Angeles","Hamburg","Frankfurt","Copenhagen","Dallas"]
    for (let i = 0 ; i<12; i++){
        cy.contains(locations[i])
    }
    cy.contains("View all locations").click({force:true})
    for (let i = 12; i<16 ; i++){
        cy.contains(locations[i])
    }
})

Cypress.Commands.add("searchforjobs",()=>{

cy.contains("Search Jobs").click({force:true})
cy.scrollTo(0,500)
cy.url().should("eq",URL.searchjoburl)
cy.contains("Studios Marketing Lead")
cy.contains("Senior Accountant")
cy.get(pages.searchbar).clear().type("Engineer")
cy.get(pages.searchicon).click({force:true})
cy.wait(3000)
cy.contains("Full-Stack Engineer – Content Platform")
cy.contains("Android Engineer – Spotify Lite")
cy.get(pages.searchbar).clear()
cy.get(pages.searchicon).click({force:true})
cy.contains("Studios Marketing Lead")
cy.contains("Senior Accountant")
cy.contains("Choose category").click({force:true})
cy.contains("Anchor").click({force:true})
cy.get(pages.jobheader).click({force:true})
cy.contains("Backend")
cy.contains("Site Reliability Engineer – Podcaster Mission")
cy.get(pages.clearicon).click({force:true})
cy.wait(3000)
cy.contains("Studios Marketing Lead")
cy.contains("Senior Accountant")
})
Cypress.Commands.add("myfavorite",()=>{

    cy.contains("Senior Accountant").click({force:true})
    cy.url().should("eq",URL.savedjobsurl)
    cy.contains("Save").click({force:true})
    cy.contains("Saved")
    cy.get(pages.heartid).click({force:true})
    cy.contains("Saved jobs")
    cy.contains("Senior Accountant")
    cy.get(pages.trachid).click({force:true})
    cy.wait(3000)
    cy.contains("No saved jobs at the moment.")
    cy.url().should("eq",URL.unsavedurl)

})
