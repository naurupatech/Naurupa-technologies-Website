// 1. Initialize Driver.js
const driver = window.driver.js.driver;

const driverObj = driver({
    showProgress: false,
    overlayColor: 'rgba(11, 28, 45, 0.85)', // Matches the hero background color (#0b1c2d)
    
    // FEATURE ADDED: Prevent closing when clicking the overlay/outside
    allowClose: false,
    

    onDestroyed: () => {
        localStorage.setItem('onboardingCompleted', 'true');
    },

    steps: [
        {
            popover: {
                title: 'Welcome to Naurupa!',
                description: 'All set UserName, let us show you the quick navigations to help you get started.'
            }
        },
        {
            element: '#courses',
            popover: {
                title: 'Our Courses',
                description: 'Explore our wide range of professional courses here.'
            }
        },
        {
            element: '#training',
            popover: {
                title: 'Specialized Training',
                description: 'Check out our hands-on training programs.',
                side: "bottom"
            }
        },
        {
            element: '#services',
            popover: {
                title: 'Our Services',
                description: 'See what Naurupa Solutions can do for your business.',
                side: "bottom"
            }
        },
        {
            element: '#enroll',
            popover: {
                title: 'Ready to Join?',
                description: 'Click here to Enroll Now and start your journey!',
                side: "bottom",
                stagePadding: "20px"
            },
        }
    ]
});

const isOnboardingCompleted = localStorage.getItem('onboardingCompleted');

// 2.For Starting the tour
if (!isOnboardingCompleted) { 
    // If onboarding is completed, do not start the tour
    driverObj.drive();
}